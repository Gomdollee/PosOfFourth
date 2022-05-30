package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.dto.Dealer;
import model.dto.Goods;
import model.dto.Ib;
import model.dto.IbLine;
import model.dto.Orders;
import model.dto.Sku;
import util.DbUtil;


public class IbDAOImpl implements IbDAO {
	
	private SkuDAO skuDao = new SkuDAOImpl();
	private GoodsDAO goodsDao = new GoodsDAOImpl();
	
	private Properties proFile = DbUtil.getProFile();
	
	/**
	 * 전체 거래처 조회
	 * */
	@Override
	public List<Dealer> dealerSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Dealer> listdealer = new ArrayList<Dealer>();
		String sql = proFile.getProperty("dealer.selectAll");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Dealer dealer = new Dealer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				listdealer.add(dealer);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return listdealer;
	}
	
	/**
	 *거래처 코드로 sku 상품 검색하기 
	 * */
	@Override
	public List<Sku> skuSelectByDealerId(String dealerId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; //select
		List<Sku> skulist = new ArrayList<>();
		String sql = proFile.getProperty("sku.selectByDealerId");
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dealerId);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Sku sku = new Sku(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				
				skulist.add(sku);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return skulist;
	}

	/**
	 *발주
	 * */
	@Override
	public int ibInsert(Ib ib) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;
		String sql=proFile.getProperty("ib.insert");
		int result=0;
		
		try {
			con =DbUtil.getConnection();
			con.setAutoCommit(false); //트랜잭션 처리 위해 자동커밋중지
			ps = con.prepareStatement(sql);
			
			ps.setString(1, ib.getDealerId());
			
			result = ps.executeUpdate();
			
			if(result == 0) {
				con.rollback();
				throw new SQLException("발주가 실패하였습니다.");
			}else {
				int line [] = ibLineUpdate(con,ib); //발주 상세 등록하기
				for(int i : line) {
					if(i != 1) {
						con.rollback();
						throw new SQLException("발주 할 수 없습니다.");
					}
				}
				con.commit();
			}
			
		}finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}



	
	/**
	 * 발주한 물품 Goods에 추가 (-> stock 추가시키기) + 발주 상세 등록하기
	 * */
	@Override
	public int[] ibLineUpdate(Connection con, Ib ib) throws SQLException {
		PreparedStatement ps = null;
		String sql = proFile.getProperty("ibline.insert");
		int result [] = null;
		try {
			ps = con.prepareStatement(sql);
			
			// ib line sql 실행 관련
			
			for (IbLine ibLine : ib.getIbLineList()) {
				// 발주 품목이 goods에 있는지 확인 & 없으면 stock 0으로 일단 추가
				Goods goods = goodsDao.goodsSelectById(ibLine.getGoodsId());
				Sku sku = skuDao.selectSkuById(ibLine.getGoodsId());
				
				if (goods == null) {
					Goods newGoods = new Goods(sku.getSkuId(), sku.getSkuName(), 0,
						(int)(2 * 1.3 * (sku.getSkuPrice())));
					goodsDao.goodsInsert(newGoods);
				}
				// 발주 항목에 대해서 ib_line 들을 참조해 stock_qty에 떼온 수량만큼 추가
				goodsDao.goodsUpdate(ibLine.getGoodsId(), ibLine.getIbQty());

				//발주 상세 등록하기
				ps.setString(1, sku.getSkuId());
				ps.setInt(2, ibLine.getIbQty());
				ps.setInt(3, (int)(1.3*(sku.getSkuPrice())));
				ps.addBatch();
            	ps.clearParameters();
			}
			result = ps.executeBatch();
		}finally {
			DbUtil.close(null, ps, null);
		}	
		return result;
	}
	

	/**
	 * 발주 내역 보기
	 * */
	@Override
	public List<Ib> selectIbByDealerId(String dealerId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; //select
		String sql =proFile.getProperty("ib.selectByDealerId");
		List<Ib> list = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dealerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ib ib = new Ib(rs.getString(1), rs.getString(2), rs.getString(3));
				
				List<IbLine> ibLine = ibTotal(ib.getIbId()); //ibid에 해당하는 발주 상세 정보 가져오기
				
				ib.setIbLineList(ibLine);
				list.add(ib);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	/**
	 *ibid에 해당하는 발주 상세 
	 * */
	@Override
	public List<IbLine> ibTotal(String ibId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; //select
		String sql =proFile.getProperty("ibline.selectIbTotalByIbId");
		List<IbLine> iblist = new ArrayList<>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, ibId);
			rs = ps.executeQuery();
			while (rs.next()) {
				IbLine ibLine = new IbLine(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				iblist.add(ibLine);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return iblist;
	}


}
