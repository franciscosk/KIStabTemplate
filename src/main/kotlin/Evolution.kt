

 fun evolution(
    population: Array<Netzwerk>, achild:Int,
    mutationRate: Double) : Array<Netzwerk> {

    /** 1.Evaluation: Fitness jeder Netzwerks herausfinden**/
for(i in population){
i.fitness=fitnessFunktion(i)

}


    /** 2.Selektion: Die fittesten Netzwerke auswählen.**/
    population.sortByDescending{ it.fitness }
    var eltern= Array<Netzwerk>(10){i ->population[i]}


    println(eltern[0].fitness.toInt())

    /** 3.Eine neue Population erstellen und diese mit mutierten Kindern der besten Netzwerke füllen.**/

    var neuePopulation: Array<Netzwerk> = Array<Netzwerk>(population.size) { Netzwerk(population[0].schichten) }



    //var neuePopulation: Array<Netzwerk> = Array<Netzwerk>(population.size) { Netzwerk(population[0].schichten) }
     for(netzwerk in eltern.indices) {
         for (aaa in 0..achild/eltern.size - 2) {
             neuePopulation[netzwerk*achild/eltern.size+aaa].netz = Array(population[netzwerk].schichten.size - 1) { i ->
                 Array(population[netzwerk].schichten[i]) { j ->
                     Neuron(
                         DoubleArray(population[netzwerk].schichten[i + 1]) { k -> population[netzwerk].netz[i][j].synapsen[k] +  (Math.random() - 0.5) * mutationRate/population[netzwerk].fitness},
                         0.0,
                         population[netzwerk].netz[i][j].bias + (Math.random() - 0.5) *  (Math.random() - 0.5) * mutationRate/population[netzwerk].fitness
                     )
                 }


             }
             neuePopulation[(netzwerk + 1) * achild / population.size - 1].netz =
                 Array(population[netzwerk].schichten.size - 1) { i ->
                     Array(population[netzwerk].schichten[i]) { j ->
                         Neuron(
                             DoubleArray(population[netzwerk].schichten[i + 1]) { k -> population[netzwerk].netz[i][j].synapsen[k] },
                             0.0,
                             population[netzwerk].netz[i][j].bias
                         )
                     }
                 }
         }}
     return neuePopulation


 }

