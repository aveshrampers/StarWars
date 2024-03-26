'use client'
import * as React from "react";
import styles from "@/app/styles/layout.module.css";
import SWImages from "@/public/images";

export const Header = () => {

  return (
    <img src={SWImages.get("/")?.src} className={styles.header}/>
  )
}
