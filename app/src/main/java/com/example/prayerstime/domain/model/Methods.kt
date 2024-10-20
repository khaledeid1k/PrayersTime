package com.example.prayerstime.domain.model


sealed class Methods(val value: Int, val name: String) {
     object DUBAI : Methods(16, "DUBAI")
     object EGYPT : Methods(5, "EGYPT")
     object FRANCE : Methods(12, "FRANCE")
     object GULF : Methods(8, "GULF")
     object ISNA : Methods(2, "ISNA")
     object JAFARI : Methods(0, "JAFARI")
     object KARACHI : Methods(1, "KARACHI")
     object KUWAIT : Methods(9, "KUWAIT")
     object MAKKAH : Methods(4, "MAKKAH")
     object MOONSIGHTING : Methods(15, "MOONSIGHTING")
     object MWL : Methods(3, "MWL")
     object QATAR : Methods(10, "QATAR")
     object RUSSIA : Methods(14, "RUSSIA")
     object SINGAPORE : Methods(11, "SINGAPORE")
     object TEHRAN : Methods(7, "TEHRAN")
     object TURKEY : Methods(13, "TURKEY")

     companion object {
          val entries = listOf(
               DUBAI, EGYPT, FRANCE, GULF, ISNA, JAFARI, KARACHI,
               KUWAIT, MAKKAH, MOONSIGHTING, MWL, QATAR, RUSSIA,
               SINGAPORE, TEHRAN, TURKEY)
     }
}
