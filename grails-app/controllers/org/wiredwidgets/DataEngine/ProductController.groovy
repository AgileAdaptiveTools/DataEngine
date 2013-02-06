package org.wiredwidgets.DataEngine

/** controller for products */
class ProductController
{
    def index()
	{
        println "===${controllerName}.index() -> ${params}"
        if ( !params.id )
		{
			render ( contentType: "text/plain", text:
"""
(Deprecated):  Please use /DataEngine/source?url=<data endpoint url> for this functionality
Processes the product at <product path> and optionally translating the result to the specified format (json, raw, xml).
Usage: /DataEngine/product/<data endpoint url>.< json | raw | xml >
""" )
		}
		else
		{
			render ( contentType: "application/json", text:
			"""{
    "datatype": "CONTAINER",
    "label": "5.5 Magnitude Earthquakes",
    "uuid": "wwf:groupmgr:0",
    "parentuuid": "wwf:groupmgr:0",
    "source": "www.usgs.gov",
    "children": [
        {
            "datatype": "EVENT",
            "label": "Loma Prieda Fault Line (5.2)",
            "description": "Loma Prieda Fault Line. Earthquake - 5.2 magnitude",
            "parentuuid": "wwf:groupmgr:0",
            "uuid": "wwf:groupmgr:1",
            "starttime": "2008-11-08T06:00Z",
            "location": {
                "type": "Point",
                "coordinates": [
                    48.8465,
                    -100.3434
                ],
                "properties": {
                    "circularError": 50.45
                }
            },
            "referenceLink": "www.usgs.gov/CA/35221.html",
            "iconLink": " www.usgs.gov/images/quake.png",
            "source": "www.usgs.gov"
        },
        {
            "datatype": "EVENT",
            "label": "San Mateo Fault Line (5.0)",
            "description": "San Mateo Fault Line. Earthquake - 5.0 magnitude",
            "parentuuid": "wwf:groupmgr:0",
            "uuid": "wwf:groupmgr:2",
            "starttime": "2008-11-09T08:20Z",
            "location": {
                "type": "Point",
                "coordinates": [
                    46.995,
                    -99.334
                ],
                "properties": {
                    "circularError": 10
                }
            },
            "referenceLink": "www.usgs.gov/CA/34421.html",
            "iconLink": " www.usgs.gov/images/quake.png",
            "source": "www.usgs.gov"
        }
    ]
}""")
		}
	}
}
