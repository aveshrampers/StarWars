"use client"
import * as React from "react";
import { Error, Infoblock, Spinner, Table } from "michael-sw-components";
import {
  useSelector,
  useDispatch,
  selectFilms,
  selectStatus,
  getFilm,
} from "@/lib/redux";
import { usePathname } from "next/navigation";
import { Description } from "./Description";
import SWImages from "@/public/images";
import styles from "@/app/styles/layout.module.css";

export function Details() {
  const dispatch = useDispatch();
  const status = useSelector(selectStatus);
  const [calledApi, setCalledApi] = React.useState(false);
  const path = usePathname();
  const films = useSelector(selectFilms);
  const thisFilm = films.find(film => (
    film.data.url === `/api${path}`
  ));

  React.useEffect(() => {
    if (!thisFilm?.detailsRetrieved) {
      dispatch(getFilm(path));
    }
    setCalledApi(true);
  }, [])

  if (calledApi && status === "idle") {
    return (
      <>
        <div className={styles.description}>
          <img src={SWImages.get(path)?.src} className={styles.image}/>
          <Description text={thisFilm?.data.opening_crawl || ""}/>
        </div>
        <div className={styles.lists}>
          <Infoblock heading="Characters" data={thisFilm?.data.characters || []}/>
          <Infoblock heading="Planets" data={thisFilm?.data.planets || []}/>
          <Infoblock heading="Species" data={thisFilm?.data.species || []}/>
          <Infoblock heading="Starships" data={thisFilm?.data.starships || []}/>
          <Infoblock heading="Vehicles" data={thisFilm?.data.vehicles || []}/>
        </div>
      </>
    );
  } else if (status === "loading") {
    return <Spinner />
  } else {
    return <Error />
  }
}