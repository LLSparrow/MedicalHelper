package com.llsparrow.healthassistant.feature_authentication_impl.data

import java.util.*

fun main() {
    val sol = Solution()
    val result = sol.lengthOfLongestSubstring("pwwkew")
    print(result)
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        val list = LinkedList<Char>()
        val set=HashSet<Char>()
        var length = 0
        var maxLenght = 0
        for (c in s) {
            if (list.contains(c)) {
                if (length > maxLenght)
                    maxLenght = length

                do {
                    var item = list.pop()
                    if (item == c) {
                        list.add(c)
                        break
                    } else length--

                } while (list.isNotEmpty())
            } else {
                list.add(c)
                length++
            }
        }
        return if (maxLenght == 0) s.length else maxLenght
    }
}