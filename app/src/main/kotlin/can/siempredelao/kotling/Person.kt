package can.siempredelao.kotling

/**
 * Created by david on 12.12.16.
 */
// In Kotlin, everything is public by default
// Constructor, just after class definition
// Open means that can be extended
open class Person(val name: String, val surname: String) {

    // Function definition
    fun getFullName() = "$name $surname"

    // Function definition (Java style)
    fun getFullName2(): String {
        return "$name $surname"
    }

    // Properties (Property == Java field + getter + setter)
    var name1 = "Name"
        set(value) { // Anyway, you can always have a custom setter
            name1 = "Name: $value" // No String.format needed! :)
        }
    var surnameb = "Surname"
}