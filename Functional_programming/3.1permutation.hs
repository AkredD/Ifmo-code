perms :: [[a]] -> [[[a]]]
perms [x:[]] = [[[x]]]
perms (listP@(y:ys):xs) = do
            c <- [0..((length listP) - 1)]
            return (map (\d -> ((!!) listP c):d) (concat (perms[(set ys y (c-1))])))
            
permutations :: [a] -> [[a]]
permutations a = concat (perms [a])


set :: [a] -> a -> Int -> [a]
set a c (-1) = a
set (x:xs) c 0 = c:xs
set (x:xs) c i = x:(set xs c (i-1))