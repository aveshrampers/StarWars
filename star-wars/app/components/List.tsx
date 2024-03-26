"use client"
import * as React from "react";
import { Error, Spinner, Table, TableData } from "michael-sw-components";
import "michael-sw-components/dist/index.css";
import {
  useSelector,
  useDispatch,
  selectFilms,
  getAllFilms,
  selectStatus,
} from "@/lib/redux";

export function ListTable() {
  const dispatch = useDispatch();
  const films = useSelector(selectFilms);
  const status = useSelector(selectStatus);
  const [calledApi, setCalledApi] = React.useState(false);

  React.useEffect(() => {
    if (films.length === 0) {
      dispatch(getAllFilms());
    }
    setCalledApi(true);
  }, [])

  if (calledApi && status === "idle") {
    const content: TableData[][] =  films.map(film => {
      const path = film.data.url;
      return [
        {
          value: film.data.title,
          type: "link",
          href: `/${path.substring(path.indexOf("films/"))}`
        },
        {
          value: String(film.data.episode_id),
          type: "text",
        },
        {
          value: film.data.director,
          type: "text",
        }
      ]
    });
  
    return (
     <Table headings={["Title", "Episode", "Director"]} data={content} />
    );
  } else if (status === "loading") {
    return <Spinner />
  } else {
    return <Error />
  }
}