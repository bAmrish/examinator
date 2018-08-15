package com.examinator.core

import com.examinator.security.authentication.User

abstract class Section {
    String subject
    String type
    String title
    String description
    List<Question> questions
}
