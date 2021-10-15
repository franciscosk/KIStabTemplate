import kotlin.math.*

fun fitnessFunktion(netz : Netzwerk):Double {
   /**fitness berechnen**/
 var   fitness= 0.0
    var zeit = 0.0
    val zeitschritt= 0.02
    val g= 9.81
    var wagenX =0.0
    var wagenV =0.0
    var wagenA= 0.0
    val wagenMasse= 1

    var stabRotation= 0.0
    var stabWinkelgeschwindigkeit=0.0
    var stabWinkelbeschleunigung = 0.0
    val stabMasse= 0.1
    var stabHalbeLaenge= 0.5

    val streckenGrenze = 2.4
    val drehGrenze = 0.209
    val kraft = 1.0




    var aBewegungen=0
    while(wagenX>=-streckenGrenze && wagenX<=streckenGrenze && stabRotation<drehGrenze && stabRotation>-drehGrenze && aBewegungen<50000){
var angewendeteKraft:Double
        var output= netz.rechnen(doubleArrayOf(wagenX,wagenV,stabRotation,stabWinkelgeschwindigkeit))

        if (output[0]<output[1])  angewendeteKraft= kraft
        else                      angewendeteKraft= -kraft

        stabWinkelbeschleunigung= ((g* sin(stabRotation)+ cos(stabRotation)*((-angewendeteKraft-stabMasse*stabHalbeLaenge*stabWinkelgeschwindigkeit.pow(2)*sin(stabRotation))/(wagenMasse+stabMasse) ))/(stabHalbeLaenge*((4/3)-(stabMasse*cos(stabRotation).pow(2))/(wagenMasse+stabMasse))))
        wagenA= ((angewendeteKraft+stabMasse*stabHalbeLaenge*((stabWinkelgeschwindigkeit.pow(2)*sin(stabRotation))-(stabWinkelbeschleunigung*cos(stabRotation))))/(wagenMasse+stabMasse))
        wagenV += zeitschritt*wagenA
        wagenX += zeitschritt*wagenV

        stabWinkelgeschwindigkeit += zeitschritt*stabWinkelbeschleunigung
        stabRotation+= zeitschritt*stabWinkelgeschwindigkeit
        aBewegungen++
        zeit+= zeitschritt


    }

   return aBewegungen.toDouble()

 }



