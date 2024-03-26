import * as React from "react";

interface Props {
  text: string;
}

export const Description = ({ text }: Props) => (
  <div>
    {text}
  </div>
);