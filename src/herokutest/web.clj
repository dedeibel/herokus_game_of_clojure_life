(ns herokutest.web
  (:use ring.adapter.jetty))

(def counter (atom 0))
(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
; currently useless, no cross request store yet
   :body (str "Hello, Ben. Visitor: " (swap! counter inc))
  }
)

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))
