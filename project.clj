(defproject herokutest "0.0.1"
  :dependencies [
    [org.clojure/clojure "1.3.0"]
    [ring/ring-jetty-adapter "1.0.1"]
    [game_of_life "1.0.0-SNAPSHOT"]
  ]
  :repositories {
    "local" ~(str (.toURI (java.io.File. "repo")))
  }
)
