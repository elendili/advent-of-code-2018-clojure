(ns clojure-time.day3.day3_1
  (:require [clojure.string :as string]
            [clojure.test :refer :all]
            [clojure.pprint :refer :all]))

(defn parse [word] (zipmap [:left :top :width :height]
                           (rest (map read-string (re-seq #"\d+" word)))))
(def test-input "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2")

(defn prepare-input-data [input-string]
  (let [as-string-list (string/split-lines input-string)]
    (into [] (map parse as-string-list))))

(defn generate-2d-array [maps-list]
  (let
    [field-width (reduce max (map #(reduce + (vals (select-keys % [:left :width]))) maps-list))
     field-height (reduce max (map #(reduce + (vals (select-keys % [:top :height]))) maps-list))]
    (make-array Integer/TYPE field-height field-width)))

(defn pprint-2d-array [ar]
  (println (map #(with-out-str (pprint %)) ar)))

(defn update-field [arr2d {:keys [left top width height]}]
  (let [x-range (range left (+ left width))
        y-range (range top (+ top height))]
    (doseq [x x-range y y-range]
      (let [
            row (aget arr2d y)
            cell-value (aget row x)]
        (aset row x (inc cell-value))))
    arr2d))



(defn shared-patches [field]
  (count (filter #(> % 1) (flatten (mapv #(into [] %) (into [] field))))))

(defn get-result [input-list]
  (let [input-maps  (prepare-input-data input-list)
        field (generate-2d-array input-maps)]
    (reduce update-field field input-maps)
    (shared-patches field)))

(get-result test-input)

(deftest a-test
  (testing (is (= 4 (get-result test-input)))))
(a-test)

(let [input-data (slurp "input")]
  (println (str "Answer: " (get-result input-data))))

