(ns clojure-time.helpers.work_with_map
  (:require [clojure.string :as string]))
(def bands [
            {:name "Brown Beaters"   :genre :rock}
            {:name "Sunday Sunshine" :genre :blues}
            {:name "Foolish Beaters" :genre :rock}
            {:name "Monday Blues"    :genre :blues}
            {:name "Friday Fewer"    :genre :blues}
            {:name "Saturday Stars"  :genre :jazz}
            {:name "Sunday Brunch"   :genre :jazz}
            ])
(println (string/join "\n" (map #(format "\"%s\"" (:name %)) bands)))
(println "\n")
(println (map #(keys %) bands))
(println "\n")
(println (map #(vals %) bands))