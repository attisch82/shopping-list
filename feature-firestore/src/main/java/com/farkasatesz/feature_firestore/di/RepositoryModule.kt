package com.farkasatesz.feature_firestore.di

import com.farkasatesz.feature_firestore.repository.BaseListRepository
import com.farkasatesz.feature_firestore.repository.CategoryRepository
import com.farkasatesz.feature_firestore.repository.ItemRepository
import com.farkasatesz.feature_firestore.repository.PriceTrackerRepository
import com.farkasatesz.feature_firestore.repository.ShoppingItemRepository
import com.farkasatesz.feature_firestore.repository.ShoppingListRepository
import com.farkasatesz.feature_firestore.repository.SupermarketRepository
import com.farkasatesz.feature_firestore.repository.UnitTypeRepository
import com.farkasatesz.feature_firestore.repositoryImpl.BaseListRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.CategoryRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.ItemRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.PriceTrackerRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.ShoppingItemRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.ShoppingListRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.SupermarketRepositoryImpl
import com.farkasatesz.feature_firestore.repositoryImpl.UnitTypeRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val repositoryModule = module {
    single { FirebaseFirestore.getInstance() }
    single<BaseListRepository> { BaseListRepositoryImpl(get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }
    single<ShoppingListRepository> { ShoppingListRepositoryImpl(get()) }
    single<ShoppingItemRepository> { ShoppingItemRepositoryImpl(get()) }
    single<PriceTrackerRepository> { PriceTrackerRepositoryImpl(get()) }
    single<SupermarketRepository> { SupermarketRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<UnitTypeRepository> { UnitTypeRepositoryImpl(get()) }
}

