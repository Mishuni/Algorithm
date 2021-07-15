from collections import defaultdict
def solution(n, results):
    cnt = 0
    
    # initial
    wins, loses = defaultdict(set), defaultdict(set)
    
    # check first
    for battle in results:
        wins[battle[0]].add(battle[1])
        loses[battle[1]].add(battle[0])
    
    # add more
    check = True
    while(check):
        check = False
        for i in range(1,n+1):
            win = wins[i].copy()
            for loser in win:
                wins[i].update(wins[loser])
                if(not check and len(win)!=len(wins[i])):
                    check = True
        
        for i in range(1,n+1):
            lose = loses[i].copy()
            for winner in lose:
                loses[i].update(loses[winner])
                if(not check and len(lose)!=len(loses[i])):
                    check = True

    for i in range(1,n+1):
        if(len(wins[i])+len(loses[i])==n-1):
            cnt=cnt+1

    return cnt


results = [[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]
print(solution(5,results))