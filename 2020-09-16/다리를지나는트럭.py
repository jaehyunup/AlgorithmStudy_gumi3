import collections;
def solution(bridge_length, weight, truck_weights):
    answer = []
    bridge=collections.deque()    
    truck_stack=[]
    count=0;
    bridge_w_sum=0;
    bridge.append(0) #0인것은 공란으로 처리.
    for i in range(len(truck_weights)-1, -1,-1):
        truck_stack.append(truck_weights[i])

    # 다리를 지난 트럭이 truck.stack의 개수와 같아질때
    while len(answer)!=len(truck_weights): # 다리를 지난 트럭수가, 지나야 하는 트럭개수와 같을때
        if len(truck_stack)>0:
            curWeight=truck_stack.pop(); # 현재들어갈 트럭을 뽑는다.
        if len(bridge)==bridge_length: # 다리가 꽉차있다면,
            isTemp=bridge.popleft()
            if isTemp!=0: #0이 아닐때만, 즉 트럭이 지나갈때만
                answer.append(isTemp) # 다리에서 한개 빼준다.
                bridge_w_sum-=isTemp
        if curWeight+bridge_w_sum > weight: # 지금 트럭이 들어가면 weight 초과될때
            truck_stack.append(curWeight); # 다시 대기
            bridge.append(0); # bridge에는 0이 들어간다.   
        else: # 들어갈 수 있을때
            bridge.append(curWeight); # bridge에는 0이 들어간다        
            bridge_w_sum+=curWeight
        #항상 카운트는 1
        count+=1
        
    return count