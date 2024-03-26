import logo from './logo.png'; 
import ep1 from './ep1.webp'; 
import ep2 from './ep2.webp'; 
import ep3 from './ep3.webp'; 
import ep4 from './ep4.webp'; 
import ep5 from './ep5.webp'; 
import ep6 from './ep6.webp'; 

const SWImages = new Map(
  [
    [ "/", logo],
    [ "/films/1", ep4],
    [ "/films/2", ep5],
    [ "/films/3", ep6],
    [ "/films/4", ep1],
    [ "/films/5", ep2],
    [ "/films/6", ep3]
  ]
);

export default SWImages;