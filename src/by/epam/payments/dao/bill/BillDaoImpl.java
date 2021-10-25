package by.epam.payments.dao.bill;

import java.util.List;

import by.epam.payments.bean.Bill;
import by.epam.payments.dao.SqlDatabaseDAO;
import by.epam.payments.dao.builders.BillBuilder;
import by.epam.payments.exception.DAOException;
import by.epam.payments.util.parameterConstants.SqlRequest;

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
