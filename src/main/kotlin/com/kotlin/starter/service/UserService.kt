package com.kotlin.starter.service

import com.kotlin.starter.model.User
import com.kotlin.starter.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(requestId: UUID): List<User> = userRepository.findAll()

    fun getUserById(id: Long, requestId: UUID): User = userRepository.findById(id).orElseThrow { Exception("User not found") }

    fun createUser(user: User, requestId: UUID): User = userRepository.save(user)

    fun updateUser(
        id: Long,
        updatedUser: User,
        requestId: UUID
    ): User {
        val user = userRepository.findById(id).orElseThrow { Exception("User not found") }
        val newUser = user.copy(username = updatedUser.username, email = updatedUser.email)
        return userRepository.save(newUser)
    }

    fun deleteUser(id: Long, requestId: UUID) = userRepository.deleteById(id)
}
