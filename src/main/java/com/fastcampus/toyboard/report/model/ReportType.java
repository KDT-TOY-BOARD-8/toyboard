package com.fastcampus.toyboard.report.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportType {
  PROFANITY("profanity", "욕설"),
  PORNOGRAPHY("pornography", "음란"),
  DEFAMATION("defamation", "비방");

  private final String key;
  private final String value;
}
