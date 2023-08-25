package com.gachon.data.source.profile

import com.gachon.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) : ProfileRepository {

}