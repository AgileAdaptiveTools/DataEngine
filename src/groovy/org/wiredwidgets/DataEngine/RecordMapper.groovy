package org.wiredwidgets.DataEngine

import org.apache.commons.logging.LogFactory
import groovy.json.JsonSlurper

/** RecordMapper takes an IDL specification's dslv, and
 *      - assigns data to the appropriate Record fields
 *          - calls the appropriate translators to format the data
 *          - remove data that is not in the specification
 */
class RecordMapper
{
    private static final log = LogFactory.getLog(this)

    Map< String, List<String> > dslvMap = null

    /** creates a new instance, reading in from the database */
    RecordMapper( int uuid )
    {
        Idl idl = Idl.findById( uuid )
        log.debug "constructor( idl=${idl?.toJson()} )"
        if ( idl )
        {
            dslvMap = [:]
            def slurper = new JsonSlurper()
            def result = slurper.parseText( idl.dslv )
            result.each  {
                dslvMap[it.key] = it.value
            }
//            println dslvMap
        }
    }


    /** processes a map of values */
    void applyIdl( Record record )
    {
        Map ext = record.ext
        log.debug "applyIdl( ext=${ext} )"
//        log.debug "applyIdl( ext=${ext}, record=${record} )"
//        log.debug "applyIdl( dslvMap=${dslvMap} )"
        if ( dslvMap )
        {
            dslvMap.each  { dslvItem ->
                println "applyIdl dslvItem=${dslvItem.key} -> ${dslvItem.value}"
                String matchKey = dslvItem.key
                int dotIndex = matchKey.indexOf(".")
                if ( dotIndex >= 0 )
                    matchKey = matchKey.substring( 0, dotIndex )
                println "applyIdl:  matchKey=${matchKey}, dotIndex=${dotIndex} -> ${dslvItem.value}"
                if ( ext.containsKey( matchKey ) )
                {
                    def item = ext.get( matchKey )
                    println "  ext key ${matchKey}(dslvMap key ${dslvItem.key}) --------->> ${item}"
                    def params = dslvMap[dslvItem.key]
//                    assert params instanceof List<String>
//                    println params
                    switch ( params[0].toLowerCase() )    // type
                    {
                        case "country":
                            processCountry( matchKey, dslvItem.key, item, params, record )
                            break;
                        case "integer":
                            processInteger( matchKey, dslvItem.key, item, params, record )
                            break;
                        case "hashmap":
                            processHashmap( matchKey, dslvItem.key, item, params, record )
                            break;
                        case "latlonalt":
                            processLatLonAltMap( matchKey, dslvItem.key, item, params, record )
                            break;
                        case "string":
                            processString( matchKey, dslvItem.key, item, params, record )
                            break;
                        case "country":
                        case "date":
                        default:
                            println "   is a ..."
                            break;
                    }
                }
            }
        }
    }


    /** processes a Lat/Lon/Alt value */
    void processCountry( String col, String dslvKey, HashMap value, List params, Record record )
    {
        log.debug "processCountry( col=${col}, dslvKey=${dslvKey}, value=${value}, params=${params}, record=\n${record} )"
        String newName = params[2]
        switch ( newName )
        {
            case "\$lat":
                record.lat = value
                break;
            case "\$lon":
                record.lon = value
                break;
            default:    // change name of key in ext
                println "old name=${col}, new name=${newName}"
                break;
        }
        log.debug "returning record=\n${record}"
    }


    /** processes a String value */
    void processInteger( String col, String dslvKey, int value, List params, Record record )
    {
        log.debug "processInteger( col=${col}, dslvKey=${dslvKey}, value=${value}, params=${params}, record=\n${record} )"
        String newName = params[1]
        switch ( newName )
        {
            case "\$uuid":
                record.uuid = value
                break;
            default:    // change name of key in ext
                println "old name=${col}, new name=${newName}"
                break;
        }
        log.debug "returning record=\n${record}"
    }


    /** processes a Lat/Lon/Alt value */
    void processHashmap( String col, String dslvKey, HashMap value, List params, Record record )
    {
        log.debug "processHashmap( col=${col}, dslvKey=${dslvKey}, value=${value}, params=${params}, record=\n${record} )"
        String newName = params[2]
        switch ( newName )
        {
            case "\$lat":
                record.lat = value
                break;
            case "\$lon":
                record.lon = value
                break;
            default:    // change name of key in ext
                println "old name=${col}, new name=${newName}"
                break;
        }
        log.debug "returning record=\n${record}"
    }


    /** processes a Lat/Lon/Alt value */
    void processLatLonAltMap( String col, String dslvKey, HashMap value, List params, Record record )
    {
        log.debug "processLatLonAltMap( col=${col}, dslvKey=${dslvKey}, value=${value}, params=${params}, record=\n${record} )"
        def subKey = dslvKey
        int dotIndex = dslvKey.indexOf(".")
        if ( dotIndex >= 0 )
            subKey = dslvKey.substring( dotIndex + 1, dslvKey.size() )
        println "processLatLonAltMap:  dslvKey=${dslvKey}, subKey=${subKey} -> ${value}"
        String newName = params[2]
        switch ( newName )
        {
            case "\$lat":
                record.lat = value.getAt( subKey )
                break;
            case "\$lon":
                record.lon = value.getAt( subKey )
                break;
            default:    // change name of key in ext
                println "old name=${col}, new name=${newName}"
                break;
        }
        log.debug "returning record=\n${record}"
    }


    /** processes a String value */
    void processString( String col, String dslvKey, String value, List params, Record record )
    {
        log.debug "processString( col=${col}, dslvKey=${dslvKey}, value=${value}, params=${params}, record=\n${record} )"
        String newName = params[1]
        switch ( newName )
        {
            case "\$title":
                record.title = value
                break;
            case "\$uuid":
                record.uuid = value
                break;
            default:    // change name of key in ext
                println "old name=${col}, new name=${newName}"
                break;
        }
        log.debug "returning record=\n${record}"
    }

}
