(ns promise-test
  (:require
    [cljs-bean.core :as cb]
    [cljs.test :as t :refer [async deftest is testing]]
    [promesa.core :as p]))


(deftest p1
  (testing "p1"
    (async done
      (-> (p/let [p1 (p/promise 1)]
            (js/console.log "p1")
            (is (= 1 p1)))
          (p/finally done)))))


(deftest p2
  (testing "p2"
    (async done
      (-> (p/let [p1 (p/promise 1)]
            (js/console.log "p2")
            (is (= 1 p1)))
          (p/finally done)))))


(defn run-tests
  []
  (t/run-tests 'promise-test))
