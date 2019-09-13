package com.santalu.textmatcher.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.santalu.textmatcher.rule.HashtagRule
import com.santalu.textmatcher.rule.MentionRule
import kotlinx.android.synthetic.main.activity_multi_rule.editText
import kotlinx.android.synthetic.main.activity_multi_rule.hashtagText
import kotlinx.android.synthetic.main.activity_multi_rule.mentionText

/**
 * Created by fatih.santalu on 9/9/2019
 */

class MultiRuleActivity : AppCompatActivity(R.layout.activity_multi_rule) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val mentionRule = MentionRule("._")
    val hashtagRule = HashtagRule("-")

    editText.addRule(mentionRule)
    editText.addRule(hashtagRule)
    mentionText.addRule(mentionRule)
    hashtagText.addRule(hashtagRule)

    editText.setOnMatchListener { rule, text ->
      when (rule) {
        is MentionRule -> {
          mentionText.text = if (text.isNullOrEmpty()) {
            getString(R.string.no_mention)
          } else {
            "mention $text"
          }
        }
        is HashtagRule -> {
          hashtagText.text = if (text.isNullOrEmpty()) {
            getString(R.string.no_hashtag)
          } else {
            "hashtag $text"
          }
        }
      }
    }

    editText.setOnMatchClickListener { showToast(it) }
  }
}