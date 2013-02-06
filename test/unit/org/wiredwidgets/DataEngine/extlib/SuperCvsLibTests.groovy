package org.wiredwidgets.DataEngine.extlib

/* Java-based tests mostly unchanged from the SuperCVS site:  http://supercsv.sourceforge.net/index.html */

import grails.test.mixin.TestFor
import org.wiredwidgets.DataEngine.Setting
import org.supercsv.io.CsvMapReader
import org.supercsv.io.ICsvMapReader
import org.supercsv.prefs.CsvPreference
import org.supercsv.cellprocessor.ift.CellProcessor
import org.supercsv.cellprocessor.constraint.NotNull
import org.supercsv.cellprocessor.constraint.UniqueHashCode
import org.supercsv.cellprocessor.constraint.StrRegEx
import org.supercsv.cellprocessor.ParseDate
import org.supercsv.cellprocessor.Optional
import org.supercsv.cellprocessor.ParseBool
import org.supercsv.cellprocessor.ParseInt
import org.supercsv.cellprocessor.constraint.LMinMax

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Setting)
class SuperCvsLibTests
{

    /** the test string */
    def csvContentString1 =
        "customerNo,firstName,lastName,birthDate,mailingAddress,married,numberOfKids,favouriteQuote,email,loyaltyPoints\n" +
        "1,John,Dunbar,13/06/1945,\"1600 Amphitheatre Parkway\n" +
        "Mountain View, CA 94043\n" +
        "United States\",,,\"\"\"May the Force be with you.\"\" - Star Wars\",jdunbar@gmail.com,0\n" +
        "2,Bob,Down,25/02/1919,\"1601 Willow Rd.\n" +
        "Menlo Park, CA 94025\n" +
        "United States\",Y,0,\"\"\"Frankly, my dear, I don't give a damn.\"\" - Gone With The Wind\",bobdown@hotmail.com,123456\n" +
        "3,Alice,Wunderland,08/08/1985,\"One Microsoft Way\n" +
        "Redmond, WA 98052-6399\n" +
        "United States\",Y,0,\"\"\"Play it, Sam. Play \"\"As Time Goes By.\"\"\"\" - Casablanca\",throughthelookingglass@yahoo.com,2255887799\n" +
        "4,Bill,Jobs,10/07/1973,\"2701 San Tomas Expressway\n" +
        "Santa Clara, CA 95050\n" +
        "United States\",Y,3,\"\"\"You've got to ask yourself one question: \"\"Do I feel lucky?\"\" Well, do ya, punk?\"\" - Dirty Harry\",billy34@hotmail.com,36"

    /**
     * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. Empty
     * columns are read as null (hence the NotNull() for mandatory columns).
     *
     * @return the cell processors
     */
    private static CellProcessor[] getProcessors() {

        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
        StrRegEx.registerMessage(emailRegex, "must be a valid email address");

//        final CellProcessor[] processors = new CellProcessor[
//            new UniqueHashCode(), // customerNo (must be unique)
//            new NotNull() // firstName
//            new NotNull() // lastName
//            new ParseDate("dd/MM/yyyy") // birthDate
//            new NotNull() // mailingAddress
//            new Optional(new ParseBool()) // married
//            new Optional(new ParseInt()) // numberOfKids
//            new NotNull() // favouriteQuote
//            new StrRegEx(emailRegex) // email
//            new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
//        ];

        final CellProcessor[] processors = new Object[10];

        processors[0] =        new UniqueHashCode() // customerNo (must be unique)
        processors[1] =        new NotNull() // firstName
        processors[2] =        new NotNull() // lastName
        processors[3] =        new ParseDate("dd/MM/yyyy") // birthDate
        processors[4] =        new NotNull() // mailingAddress
        processors[5] =        new Optional(new ParseBool()) // married
        processors[6] =        new Optional(new ParseInt()) // numberOfKids
        processors[7] =        new NotNull() // favouriteQuote
        processors[8] =        new StrRegEx(emailRegex) // email
        processors[9] =        new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints

        return processors;
    }

    void testMapReader() {
        ICsvMapReader mapReader = null;
        try {
            mapReader = new CsvMapReader(new StringReader(csvContentString1), CsvPreference.STANDARD_PREFERENCE);

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            Map<String, Object> customerMap;
            while( (customerMap = mapReader.read(header, processors)) != null ) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, customerMap=%s", mapReader.getLineNumber(),
                        mapReader.getRowNumber(), customerMap));
            }

        }
        finally {
            if( mapReader != null ) {
                mapReader.close();
            }
        }
    }
}
