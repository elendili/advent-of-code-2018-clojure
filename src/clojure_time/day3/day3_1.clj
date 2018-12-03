(ns clojure-time.day3.day3_1
  (:require [clojure.string :as string]
            [clojure.test :refer :all]))

(defn parse [word] (re-seq #"\\d+" word))
(def input-line "#1 @ 286,440: 19x24")
(println input-line "\n"  (rest (re-seq #"\d+" input-line)))
;
;(let [file-content (slurp "input")
;      as-string-list (string/split-lines file-content)
;      as-maps-list ((reduce parser as-string-list))])
  ;(println as-maps-list))
