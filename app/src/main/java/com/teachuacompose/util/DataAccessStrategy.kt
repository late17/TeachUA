package com.teachuacompose.util

suspend fun <T> performGetFromRemote(
    networkCall: suspend () -> Resource<T>
) : Resource<T> {
    return networkCall.invoke()
}

suspend fun <UI, ENTITY> performGetFromDb(
    dbCall : suspend () -> ENTITY,
    entityToUi : suspend (ENTITY) -> UI
) : Resource<UI> {
    val invoke = dbCall.invoke()
    return if (invoke == null) {
        Resource.error()
    } else {
        Resource.success( entityToUi(invoke) )
    }
}

//suspend fun <UI, ENTITY, DTO> performGetFromDbAndRemote(
//    dbCall : suspend () -> ENTITY,
//    entityToUi : suspend (ENTITY) -> UI,
//    dtoToEntity : suspend (DTO) -> ENTITY,
//    saveToDatabase : suspend (Entity) -> Unit,
//    networkCall: suspend () -> Resource<DTO>,
//    ): Resource<UI> {
//    val dbResult = performGetFromDb(
//        dbCall = dbCall,
//        entityToUi = entityToUi
//    )
//    return if (dbResult.status == Resource.Status.SUCCESS) {
//        val entitity = dtoToEntity(dbResult)
//        saveToDatabase(entitity)
//        return entityToUi(entitity)
//    } else {
//        performGetFromRemote(networkCall)
//    }
//}
