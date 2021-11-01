package by.epam.payment.dao.bill;

import java.util.List;

import by.epam.payment.bean.Bill;
import by.epam.payment.dao.SqlDatabaseDAO;
import by.epam.payment.dao.builder.BillBuilder;
import by.epam.payment.exception.DAOException;
import by.epam.payment.util.parameterConstant.SqlRequest;

public class BillDaoImpl extends SqlDatabaseDAO<Bill> implements BillDao {
	
	private BillBuilder billBuilder = new BillBuilder();

	@Override
	public List<Bill> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertInto(Bill bill) throws DAOException {
		return super.addNewData(SqlRequest.ADD_BILL, bill, billBuilder);
	}

	@Override
	public Bill findEntityById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill update(Bill entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Bill entity) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

}
