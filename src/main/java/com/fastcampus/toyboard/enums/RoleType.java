package com.fastcampus.toyboard.enums;

public enum RoleType {
  ROLE_BLACKLIST("블랙리스트"),
  ROLE_SPROUT("새싹회원"),
  ROLE_GREAT("우수회원");

  private String description;

  RoleType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
