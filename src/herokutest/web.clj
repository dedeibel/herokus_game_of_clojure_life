(ns herokutest.web
  (:use [game_of_life.cell :only (new_cell)])
  (:use [game_of_life.world_printer :only (to_string)])
  (:use [game_of_life.world_builder :only (from_string)])
  (:use [game_of_life.game :only (next_generation)])
  (:use ring.adapter.jetty)
)

(def twentyfour_and_more
"xx
,xx
,
,
,
,                                   x x  xxx
,                                   x x    x
,                                   xxx  xxx
,                                     x  x
,                                     x  xxx
,                                                                                                   xx  
,                                                                                                  x  x
,                                                                                                 x    x
,                                                                                                x      x
,                                                                                                x      x
,                                                                                                 x    x
,                                                                                                  x  x
,                                                                                                   xx"
)

(def counter (atom 0))
(def world (atom (from_string twentyfour_and_more new_cell)))

(defn app [req]
  (let [current @world]
    (swap! counter inc)
    (swap! world next_generation)
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body 
       (str "Game Of Life (Step " @counter "):\n" (to_string current))
    }
  )
)

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))


