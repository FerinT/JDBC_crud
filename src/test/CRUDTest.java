import main.CrudOperations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tayfer01 on 7/26/2016.
 */

public class CRUDTest {

    CrudOperations crudOperations = null;
    boolean isValid;

    @Before
    public void setUp() throws Exception {
        crudOperations = new CrudOperations();
    }

    /*All asserts are done in one method as there is no way to stipulate test order in this version of junit
    * therefore insert was happening before create etc*/

    @Test
    public void testCreateInsertUpdateDelete() throws Exception {
        isValid = crudOperations.createTable();
        Assert.assertTrue(isValid);

        isValid = crudOperations.insertData();
        Assert.assertTrue(isValid);

        isValid = crudOperations.updateRecord("Finance");
        Assert.assertTrue(isValid);

        isValid = crudOperations.removeRecord();
        Assert.assertFalse(isValid);

        isValid = crudOperations.retrieveData();
        Assert.assertFalse(isValid);

    }
}
