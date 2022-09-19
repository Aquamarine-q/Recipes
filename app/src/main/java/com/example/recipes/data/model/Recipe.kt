package com.example.recipes.data.model

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val veryHealthy: Boolean,
    val cheap: Boolean,
    val veryPopular: Boolean,
    val sustainable: Boolean,
    val lowFodmap: Boolean,
    val weightWatcherSmartPoints: Int,
    val gaps: String?,
    val preparationMinutes: Int,
    val cookingMinutes: Int,
    val aggregateLikes: Int,
    val healthScore: Int,
    val pricePerServing: Double,
    val extendedIngredients: List<ExtendedIngredient>,
    val id: Long,
    val title: String?,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceURL: String?,
    val image: String?,
    val imageType: String?,
    val summary: String?,
    val cuisines: List<String>,
    val dishTypes: List<String>,
    val diets: List<String>,
    val occasions: List<String>,
    val instructions: String?,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val sourceName: String?,
    val creditsText: String?,
    val spoonacularSourceURL: String?,
    val license: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        listOf<ExtendedIngredient>().apply {
            parcel.readList(this, ExtendedIngredient::class.java.classLoader)
        },
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString(),
        listOf<AnalyzedInstruction>().apply {
            parcel.readList(this, AnalyzedInstruction::class.java.classLoader)
        },
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (vegetarian) 1 else 0)
        parcel.writeByte(if (vegan) 1 else 0)
        parcel.writeByte(if (glutenFree) 1 else 0)
        parcel.writeByte(if (dairyFree) 1 else 0)
        parcel.writeByte(if (veryHealthy) 1 else 0)
        parcel.writeByte(if (cheap) 1 else 0)
        parcel.writeByte(if (veryPopular) 1 else 0)
        parcel.writeByte(if (sustainable) 1 else 0)
        parcel.writeByte(if (lowFodmap) 1 else 0)
        parcel.writeInt(weightWatcherSmartPoints)
        parcel.writeString(gaps)
        parcel.writeInt(preparationMinutes)
        parcel.writeInt(cookingMinutes)
        parcel.writeInt(aggregateLikes)
        parcel.writeInt(healthScore)
        parcel.writeDouble(pricePerServing)
        parcel.writeList(extendedIngredients)
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeInt(readyInMinutes)
        parcel.writeInt(servings)
        parcel.writeString(sourceURL)
        parcel.writeString(image)
        parcel.writeString(imageType)
        parcel.writeString(summary)
        parcel.writeStringList(cuisines)
        parcel.writeStringList(dishTypes)
        parcel.writeStringList(diets)
        parcel.writeStringList(occasions)
        parcel.writeString(instructions)
        parcel.writeList(analyzedInstructions)
        parcel.writeString(sourceName)
        parcel.writeString(creditsText)
        parcel.writeString(spoonacularSourceURL)
        parcel.writeString(license)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}