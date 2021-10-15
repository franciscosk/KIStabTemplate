import java.io.File
import kotlin.random.Random

class Netzwerk(var schichten : Array<Int>, var fitness:Double=0.0) {
    /**
     Dem Constructor wird ein IntArray übergeben, bei dem jeder Integer die Anzahl der Neuronen einer Schicht darstellt.
     Mit diesem Array wird dann das tatsächliche Netz (var Netz) mit zufälligen Gewichten nach der vorgegeben Topologie erstellt.
     Die Fitness kann im vorhinein übergeben werden, sie wird jedoch beim Späteren lernen Überschrieben.
     **/
    var netz = Array(schichten.size-1){i -> Array(schichten[i]){Neuron(DoubleArray(schichten[i+1]) { (Random.nextDouble()-0.5) }, 0.0, (Random.nextDouble()-0.5))} }
fun mitDatenfüttern(file: File){
    var a = file.readLines()
}
    fun rechnen(input: DoubleArray) : DoubleArray{
/** jedem Inputneuron des KNN seinen Wert aus dem gegebenen DoubleArray zuweisen **/
        for(inputneuron in 0 until schichten[0]){
            netz[0][inputneuron].wert = input[inputneuron]
        }
        /** mit umschriebener Matrixmultiplikation schicht für schicht die Neuronenwerte berechnen*/
        for(schicht in 1 until netz.size){
            for(neuron in netz[schicht].indices){
                var tmp : Double = 0.0
                for(neuronVorgänger in netz[schicht-1].indices){
                    tmp+= netz[schicht-1][neuronVorgänger].wert*netz[schicht-1][neuronVorgänger].synapsen[neuron]
                }
                netz[schicht][neuron].wert = ReLU(tmp+netz[schicht][neuron].bias)
            }
        }
        var output = DoubleArray(schichten.last()){0.0}
        for(outputNeuron in output.indices){
            var tmp : Double = 0.0
            for(i in netz.last().indices){
                tmp+= netz.last()[i].wert*netz.last()[i].synapsen[outputNeuron]
            }
            output[outputNeuron] = ReLU(tmp)
        }
        return output
    }

    fun print(){
        for(a in netz.indices){
            for(b in netz[a].indices){
                print("(")
                print(netz[a][b].bias)
                print(",")
                for(aac in netz[a][b].synapsen.indices){
                    print(netz[a][b].
                    synapsen[aac])
                    print(" ")
                }
                print(")")
            }
            println()
        }
    }
    override fun toString() : String {
        var s = ""
        for(schicht in 0..netz.size-1){
            for(neuron in 0..netz[schicht].size-1){
                s+= netz[schicht][neuron].bias
                s+= " "
                for(synapse in netz[schicht][neuron].synapsen.indices){
                    s+= netz[schicht][neuron].synapsen[synapse]
                    s+= " "
                }
            }
            s+="a"
        }
        return s
    }
}
fun ReLU (Input : Double) : Double {
    return if(Input>0) Input
    else 0.0
}
