package com.fastcampus.toyboard.report.dto;

import com.fastcampus.toyboard.report.model.ReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReportRequestDto {
  private final ReportType reason;
  private final String detail;
}
