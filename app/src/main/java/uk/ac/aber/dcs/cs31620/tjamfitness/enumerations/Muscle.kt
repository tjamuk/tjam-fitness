package uk.ac.aber.dcs.cs31620.tjamfitness.enumerations

enum class Muscle(val string: String)
{
    //Abs muscles
    LOWER_ABS("Lower"),
    MIDDLE_ABS("Middle"),
    UPPER_ABS("Upper"),

    //Back muscles
    ERECTOR_SPINAE("Erector Spinae"),
    LATS("Lats"),
    RHOMBOIDS("Rhomboids"),
    TRAPS("Traps"),

    //Bicep muscles
    BICEPS_SHORT_HEAD("Short Head"),
    BICEPS_LONG_HEAD("Long Head"),

    //Chest muscles
    LOWER_CHEST("Lower"),
    MIDDLE_CHEST("Middle"),
    UPPER_CHEST("Upper"),

    //Deltoid muscles
    FRONT_DELTS("Front"),
    REAR_DELTS("Rear"),
    SIDE_DELTS("Side"),

    //Leg muscles
    CALVES("Calves"),
    GLUTES("Glutes"),
    HAMSTRINGS("Hamstrings"),
    QUADS("Quads"),

    //Back muscles
    TRICEPS_LATERAL_HEAD("Lateral Head"),
    TRICEPS_LONG_HEAD("Long Head"),
    TRICEPS_MEDIAL_HEAD("Medial Head"),
}