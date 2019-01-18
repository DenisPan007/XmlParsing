package service.validate;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorSaxXsdTest {

    @Test
    public void validate() throws ValidatorException {
        String fileName = "src/test/resources/tariffs_files/Tariffs.xml";
        String schemaName = "src/test/resources/tariffs_files/TariffsXsd.xsd";
        ValidatorSaxXsd validator = new ValidatorSaxXsd();
        validator.validate(fileName,schemaName);
    }
}