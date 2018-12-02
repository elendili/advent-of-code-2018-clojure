(ns clojure-time.day1.day1_1
  ;(:require [clojure.java.io :as io])
  (:require [clojure.string :as string]))


(let [file-content (slurp "input")
      as-string-list (string/split-lines file-content)
      el1 (nth as-string-list 0)
      num1 (Integer. el1)
      as-number-list (map read-string as-string-list)
      summary (reduce + as-number-list)]

  (println file-content)
  (println as-string-list)
  (println el1)
  (println num1)
  (println as-number-list)
  (println summary))


