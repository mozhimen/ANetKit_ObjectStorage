package com.mozhimen.netk.objectstorage.cos

/**
 * @ClassName IFetchCredentialsListener
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/12/21 22:35
 * @Version 1.0
 */
interface IFetchCredentialsListener {
    fun onFetchNewCredentials(): COSCredentials
}