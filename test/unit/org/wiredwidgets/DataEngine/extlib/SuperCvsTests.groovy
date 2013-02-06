package org.wiredwidgets.DataEngine.extlib

/* groovy-based tests */

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
class SuperCvsTests
{
    /**
     * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. Empty
     * columns are read as null (hence the NotNull() for mandatory columns).
     *
     * @return the cell processors
     */
    private static CellProcessor[] getProcessors( int n ) {

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

        final CellProcessor[] processors = new Object[n];
        for ( int i=0; i<n; i++ )
        {
            processors[i] = new Optional()
        }

        return processors;
    }

    void testMapReader() {
        ICsvMapReader mapReader = null;
        try
        {
            URL url = new URL("http://localhost:8080/DataEngine/source?url=http://localhost:8080/DataEngine/sample_files/simple.csv")
            mapReader = new CsvMapReader( new StringReader( url.text ), CsvPreference.STANDARD_PREFERENCE )

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader( true )
            final CellProcessor[] processors = getProcessors( header.length );

//            Map<String, Object> customerMap;
            org.wiredwidgets.DataEngine.Record customerMap
            while( ( customerMap = mapReader.read(header, processors ) ) != null )
            {
                println "${mapReader.getLineNumber()}.${mapReader.getRowNumber()}, ${customerMap.sort{it.key}}"
            }

        }
        finally {
            if( mapReader != null )
            {
                mapReader.close()
            }
        }
    }
}
