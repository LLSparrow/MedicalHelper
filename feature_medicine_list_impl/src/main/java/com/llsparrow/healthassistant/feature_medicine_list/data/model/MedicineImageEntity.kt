package com.llsparrow.healthassistant.feature_medicine_list.data.model

import androidx.room.*
import com.llsparrow.core_base_api.data.BaseEntity

@Entity(
    tableName = "medicine_images",
    indices = [
        Index(value = ["medicine_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = MedicineEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("medicine_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MedicineImageEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long = 0,
    @ColumnInfo(name = "medicine_id") val medicineId: Long,
    @ColumnInfo(name = "path") val path: String
) : BaseEntity