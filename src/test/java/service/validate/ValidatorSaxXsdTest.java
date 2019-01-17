package service.validate;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorSaxXsdTest {

    @Test
    public void validate() throws ValidatorException {
        String fileName = "Tariffs.xml";
        String schemaName = "TariffsXsd.xsd";
        ValidatorSaxXsd validator = new ValidatorSaxXsd();
        validator.validate(fileName,schemaName);
    }
}