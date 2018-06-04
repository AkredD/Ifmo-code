perms:: [a] -> [[a]]
perms [] = [[]]
perms a = concat (map nextPerm (formation a (toInteger (length a))))

formation:: [a] -> Integer -> [[[a]]]
formation a 1 = [[a]]
formation a@(x:xs) i = [((get a (i-1)):(set xs x (i-2)))]:(formation a (i-1))

set:: [a] -> a -> Integer -> [a]
set (x:xs) c 0 = c:xs
set (x:xs) c i = x:(set xs c (i-1))

get:: [a] -> Integer -> a
get (x:xs) 0 = x
get (x:xs) i = get xs (i-1)

nextPerm:: [[a]] -> [[a]]
nextPerm [] = []
nextPerm ((y:[]):xs) = [[y]]
nextPerm ((y:ys):xs) = map (\x -> (y:x)) (perms ys)

