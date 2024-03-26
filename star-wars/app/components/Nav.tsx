"use client";

/* Core */
import Link from "next/link";
import { usePathname } from "next/navigation";

/* Instruments */
import styles from "../styles/layout.module.css";

import ArrowBackIcon from '@mui/icons-material/ArrowBack';

export const Nav = () => {
  const pathname = usePathname();

  if (pathname === "/") {
    return <></>
  } else return (
    <nav className={styles.nav}>
      <Link
        className={styles.link}
        href="/"
      >
        <ArrowBackIcon />
        Back
      </Link>
    </nav>
  );
};
