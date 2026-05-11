
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanAccountService {
	public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

	    List<LoanAccount> result = new ArrayList<>();
	    if (accounts == null) {
	        return result;
	    }
	    Date currentDate = new Date();
	    for (LoanAccount account : accounts) {
	        if (account == null) {
	            continue;
	        }
	        if (account.getDueDate() != null &&
	            account.getDueDate().before(currentDate)) {
	            if (account.getOutstandingBalance() > 0) {
	                result.add(account);
	            }
	        }
	    }
	    return result;
	}
}
	