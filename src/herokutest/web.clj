(ns herokutest.web
  (:use ring.adapter.jetty))

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
; currently useless, no cross request store yet
   :body (str "Hello, Ben. Visitor: " (let [counter (agent 0)]
       (send counter inc)
       (await-for 100 counter)
       (deref counter))
     )
  }
)

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))
