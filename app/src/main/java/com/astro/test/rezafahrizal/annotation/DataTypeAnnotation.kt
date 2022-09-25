package com.astro.test.rezafahrizal.annotation

import androidx.annotation.StringDef

annotation class DataTypeAnnotation() {
    @StringDef(SortMode.ASC, SortMode.DESC)
    @Retention(AnnotationRetention.SOURCE)
    annotation class SortMode {
        companion object {
            const val ASC = "modeASC"
            const val DESC = "modeDESC"
        }
    }

    @StringDef(FaveMode.INSERT, FaveMode.DELETE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class FaveMode {
        companion object {
            const val INSERT = "modeInsert"
            const val DELETE = "modeDelete"
        }
    }
}
