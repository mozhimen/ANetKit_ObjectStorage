package com.mozhimen.netk.objectstorage.os.commons

/**
 * @ClassName IOSProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/12/21
 * @Version 1.0
 */
interface IOSProxy {
    fun strTimeExpiration2longTimeExpiration(strDate: String): Long?
}