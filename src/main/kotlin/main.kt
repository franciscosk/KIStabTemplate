import java.io.File


fun main() {
	/** Population erstellen:
	 * Netwerk-Arraygröße = Anzahl der Netzwerke in einer Population
	 * das IntArray ist für die Netztopologie zuständing:
	 * jeder zahl representiert eine Schicht, ihr Wert die Anzahl der Neuronen der schicht
	 * die erste zahl (links) steht für die Inputschicht, die letzte (rechts) für die Outputschicht
	 **/
	var population = Array<Netzwerk>(100) { Netzwerk(arrayOf(4, 4, 4, 2)) }
	var evolutionsschritte = 10
	evolutionSchritte(evolutionsschritte, population)
	//bestenSpeichern(population)
}

fun bestenSpeichern(population: Array<Netzwerk>){
/** ein Weg die netwerke abzuspeichern:**/
population.sortByDescending { it.fitness }
		var a = population[0].toString()
		var file = File("Netzwerk$a.txt")
		file.printWriter().use { out ->

			for (aaa in a.indices) {
				out.println(a[aaa])
			}
		}}


fun evolutionSchritte( schritte:Int, startPopulation: Array<Netzwerk>,mutationsRate:Double= 100.00, populationsGroesse: Int= 100){
	var population= startPopulation
	for ( i in 1 until schritte){

		var neuepopulation =  evolution(population,populationsGroesse,mutationsRate)
		population= neuepopulation
	}
}
fun evolutionAbbruchkriterium(fitness:Double, startPopulation: Array<Netzwerk>,mutationsRate:Double= 100.00, populationsGroesse: Int= 100){
	var population= startPopulation
	population.sortByDescending { it.fitness }
	var bestfitness= population[0].fitness
	while(fitness<bestfitness){

		var neuepopulation =  evolution(population,populationsGroesse,mutationsRate)
		population= neuepopulation
		population.sortByDescending { it.fitness }
		bestfitness= population[0].fitness
	}
}


