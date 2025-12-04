package com.example.inventory.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInventoryDatabase(@ApplicationContext context: Context): InventoryDatabase {
        return Room.databaseBuilder(
            context,
            InventoryDatabase::class.java,
            "item_database"
        ).build()
    }

    @Provides
    fun provideItemDao(inventoryDatabase: InventoryDatabase): ItemDao {
        return inventoryDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideItemsRepository(itemDao: ItemDao): ItemsRepository {
        return OfflineItemsRepository(itemDao)
    }
}
