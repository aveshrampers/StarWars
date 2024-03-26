/* Components */
import { Providers } from "@/lib/providers";

/* Instruments */
import styles from "./styles/layout.module.css";
import "./styles/globals.css";

import { Header } from "./components/Header";
import { Nav } from "./components/Nav";

export default function RootLayout(props: React.PropsWithChildren) {

  return (
    <Providers>
      <html lang="en">
        <body>
          <section className={styles.container}>
            <Nav/>

            <Header/>

            <main className={styles.main}>{props.children}</main>
          </section>
        </body>
      </html>
    </Providers>
  );
}
